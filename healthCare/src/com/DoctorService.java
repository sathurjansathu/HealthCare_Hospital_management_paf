package com;

import Person.Doctor;


import java.sql.PreparedStatement;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Doctor")
public class DoctorService {

	Doctor DocObject = new Doctor();
	String output;
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readDoctor()
	 {
	 return DocObject.readDoctor();
	 }

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDoctor(@FormParam("DoctorID") String DoctorID,
	 @FormParam("Name") String Name,
	 @FormParam("Address") String Address,
	 @FormParam("Gender") String Gender,
	 @FormParam("Specialization") String Specialization,
	@FormParam("RegisteredHospital") String RegisteredHospital	,
	@FormParam("Email") String Email,
	@FormParam("Phone") String Phone)
	
	{
		return  output = DocObject.insertDoctor(DoctorID ,Name,Address,Gender,Specialization,RegisteredHospital,Email, Phone);
//output;
	}

	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String Details)
	{
	//Convert the input string to a JSON object
	 JsonObject itemObject = new JsonParser().parse(Details).getAsJsonObject();
	//Read the values from the JSON object
	 String DoctorID = itemObject.get("DoctorID").getAsString();
	 String Name = itemObject.get("Name").getAsString();
	 String Address = itemObject.get("Address").getAsString();
	 String Gender = itemObject.get("Gender").getAsString();
	 String Specialization = itemObject.get("Specialization").getAsString();
	 String RegisteredHospital = itemObject.get("RegisteredHospital").getAsString();
	 String Email = itemObject.get("Email").getAsString();
	 String Phone = itemObject.get("Phone").getAsString();
	 String output = DocObject.updateDoctor(DoctorID ,Name,Address,Gender,Specialization,RegisteredHospital,Email, Phone);
	
	 
	 return output;
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String Details)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(Details, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String DoctorID = doc.select("DoctorID").text();
	 String output = DocObject.deleteDoctor(DoctorID);
	return output;
	}


}
