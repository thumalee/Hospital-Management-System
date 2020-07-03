package com.oop.bill;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.oop.pharmacist.Pharmacist;

/**
 * Servlet implementation class BillControllerServlet
 */
@WebServlet("/BillControllerServlet")
public class BillControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BillDbUtil billDbUtil;
	
	@Resource(name="jdbc/Hospital")
	private DataSource dataSource;
	

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		//create our bill db util.....and pass in the connn pool
				try
				{
					billDbUtil=new BillDbUtil (dataSource);
				}catch(Exception e)
				{
					throw new ServletException(e);
				}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			//read the "command" parameter
			String theCommand=request.getParameter("command");
			
			//if the command is missing,then default to listing bill
			if(theCommand==null)
			{
				theCommand="LIST";
			}
			//route to the appropriate method
			switch(theCommand)
			{
			//list the bill
			case"LIST":
				listBill(request,response);
				break;
			
			case "ADD":
				addBill(request,response);
				break;
				
			case "LOAD":
				loadBill(request,response);
				break;
				
			case "UPDATE":
				updateBill(request,response);
				break;
				
			default:
				listBill(request,response);
			}
		}
		catch (Exception e) 
		{
			throw new ServletException(e);
		}
	
	}


	private void updateBill(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
			
			String id=request.getParameter("bill_id");
			Double doc=Double.parseDouble(request.getParameter("doc"));
			Double lab=Double.parseDouble(request.getParameter("lab"));
			Double med=Double.parseDouble(request.getParameter("med"));
			Double hos=Double.parseDouble(request.getParameter("hosp"));
			String rec=request.getParameter("recpid");
			
			Bill theBill = new Bill(id,doc,lab,med,hos,rec);
			
			billDbUtil.updateBill(theBill);
			
			listBill(request,response);
		
	}
	
	
	private void loadBill(HttpServletRequest request, HttpServletResponse response)throws Exception {
		// TODO Auto-generated method stub
		
		String theBillId=request.getParameter("bill_id");
		
		Bill thebill=billDbUtil.getBill(theBillId);
		
		request.setAttribute("THE_BILL", thebill);
		
		RequestDispatcher dispatcher=
				request.getRequestDispatcher("/update-bill.jsp");
		dispatcher.forward(request,response);
		
		
	}


	private void addBill(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//read bill info from form data
		String billid=request.getParameter("id");
		Double docfee=Double.parseDouble(request.getParameter("doc"));
		Double labfee=Double.parseDouble(request.getParameter("lab"));
		Double medfee=Double.parseDouble(request.getParameter("med"));
		Double hospfee=Double.parseDouble(request.getParameter("hosp"));
		//Double tot=Double.parseDouble(request.getParameter("tot"));
		String recep=request.getParameter("recpid");
		

		//create a new  bill object
		Bill theBill=new Bill(billid,docfee,labfee,medfee,hospfee,recep);
		//add the bill to database
		billDbUtil.addBill(theBill);
				
		//send back to main page
		listBill(request,response);
	}

	private void listBill(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//get bill from dbutil
		List<Bill> bill=billDbUtil.getBill();
				
				
		//add bills to request
		request.setAttribute("Bill_list",bill );
				
				
		//send to jsp(view)
		RequestDispatcher dispatcher=request.getRequestDispatcher("/list-bill.jsp");
		dispatcher.forward(request, response);
		
	}

}
