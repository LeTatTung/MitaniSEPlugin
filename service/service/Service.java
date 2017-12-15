package service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.xml.namespace.QName;

import ws.data.DataServiceDelegate;
import ws.data.DataServiceService;
import ws.owl.WebServiceDelegate;
import ws.owl.WebServiceService;

public class Service {
	public static Boolean isLogin=false;
	public static Boolean isAdmin=false;
	public static String username;
	public static String password;
	public static String uri;
	public static String uriProjectTeam;
	
	public static WebServiceDelegate webServiceDelegate;
	public static DataServiceDelegate dataServiceDelegate;

	public static void logOn(String username, String password, String uri, String wsdlLocation)
	{
		Service.username = username;
		Service.password = password;
		Service.uri = uri;
		Service.isLogin = true;
		Service.isAdmin = false;
		
		if (password.equals("admin"))
			Service.isAdmin = true;
		
		setService(wsdlLocation);
	}
	public static void logOut()
	{
		Service.username = null;
		Service.password = null;		
		Service.uri = null;
		Service.uriProjectTeam = null;
		Service.isLogin = false;
		Service.isAdmin = false;
		
		dataServiceDelegate = null;
		webServiceDelegate = null;
	}
	
	public static void setService(String wsdlLocation)
	{
		System.out.println("setServive");
		dataServiceDelegate = createDataServiceDelegate(wsdlLocation);
		webServiceDelegate = createWebServiceDelegate(wsdlLocation);
	}

	public static DataServiceDelegate createDataServiceDelegate(String wsdlLocation)
	{
		if (!wsdlLocation.endsWith("/"))
			wsdlLocation += "/";
		
		try {
			System.out.println("Ran here!");
			DataServiceService serviceData = new DataServiceService(new URL(wsdlLocation+"SEService/DataServicePort?WSDL"), new QName("http://data.ws/","DataServiceService"));
			return serviceData.getDataServicePort();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static WebServiceDelegate createWebServiceDelegate(String wsdlLocation)
	{
		if (!wsdlLocation.endsWith("/"))
			wsdlLocation += "/";
		
		try {
			System.out.println("createWebservice");
			WebServiceService serviceWeb = new WebServiceService(new URL(wsdlLocation+"SEService/WebServicePort?WSDL"), new QName("http://owl.ws/","WebServiceService"));

			return serviceWeb.getWebServicePort();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
