package com.login.medicine;

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


/**
 * Servlet implementation class MedicineControllerServlet
 */
@WebServlet("/MedicineControllerServlet")
public class MedicineControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
private MedicineDbUtil medicineDbUtil;
	
	@Resource(name="jdbc/Hospital")
	private DataSource dataSource;
	
	
	
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		//create our medicine db util.....and pass in the connn pool
		try
		{
			medicineDbUtil=new  MedicineDbUtil(dataSource);
		}catch(Exception e)
		{
			throw new ServletException(e);
		}
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
		//read the "command" parameter
		String theCommand=request.getParameter("command");
				
		//default lisitin
		if(theCommand==null)
		{
			theCommand="LIST";
		}
		
		//route to the appropriate method
		switch(theCommand)
		{//list tje medicine...in MVC fashion
		case "LIST":
			listMedicine(request,response);
			break;
			
			
		case "ADD":
			addMedicine(request,response);
			break;
		
		case "LOAD":
			loadMedicine(request,response);
			break;
			
		case "UPDATE":
			updateMedicine(request,response);
			break;
		
		case "DELETE":
			deleteMedicine(request,response);
			break;
			
		default:
			listMedicine(request,response);
		
		
		
			}
		
		}
		catch(Exception e)
		{
			throw new ServletException(e);
		}
	
		
	}



	private void deleteMedicine(HttpServletRequest request, HttpServletResponse response)throws Exception {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(request.getParameter("medicineId"));
		
		medicineDbUtil.deleteMedicine(id);
		
		listMedicine(request,response);
	}



	private void updateMedicine(HttpServletRequest request, HttpServletResponse response)throws Exception {
		// TODO Auto-generated method stub
		
		//read medicine info
		int id=Integer.parseInt(request.getParameter("medicineId"));
		String name=request.getParameter("medname");
		int amount=Integer.parseInt(request.getParameter("amount"));
		double price=Double.parseDouble(request.getParameter("price"));
		String uid=request.getParameter("ph_id");
		
		//new object
		Medicine theMedicine = new Medicine(id,name,amount,price,uid);
		
		medicineDbUtil.updateMedicine(theMedicine);
		
		
		
		listMedicine(request,response);
		
	}



	private void loadMedicine(HttpServletRequest request, HttpServletResponse response)throws Exception {
		// TODO Auto-generated method stub
		
		//read medicine id from form data
		String theMedicineId=request.getParameter("medicineId");
		
		
		//get medicne from db util
		Medicine theMedicine=medicineDbUtil.gotMedicine(theMedicineId);
		
		//place medicine in request attribute
		request.setAttribute("THE_MEDICINE", theMedicine);
		
		//send to jsp page
		RequestDispatcher dispatcher=
				request.getRequestDispatcher("/update-medicine-form.jsp");
		dispatcher.forward(request,response);
	}



	private void addMedicine(HttpServletRequest request, HttpServletResponse response)throws Exception {
		// TODO Auto-generated method stub
		
		
		//read medince infor from form data
		//Integer id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("medname");
		Integer amount=Integer.parseInt(request.getParameter("amount"));
		Double price=Double.parseDouble(request.getParameter("price"));
		String eid=request.getParameter("ph_id");
		
		
		// create new medicine object
		Medicine theMed= new Medicine(name,amount,price,eid);
		
		//add to db
		medicineDbUtil.addMedicine(theMed);
		
		//send to main page
		listMedicine(request,response);
	}



	private void listMedicine(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//get medicine from db util
		List<Medicine> medicine=medicineDbUtil.getMedicine();
		
		//add medicne from db util
		request.setAttribute("MEDICINE_LIST", medicine);
		
		//send to jsp view(view)
		RequestDispatcher dispatcher=request.getRequestDispatcher("/list-medicine.jsp");
		dispatcher.forward(request, response);
		
	}



}
