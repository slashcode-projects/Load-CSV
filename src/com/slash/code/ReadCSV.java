package com.slash.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV {

	public static void main(String[] args) {
		List<Customer> custList = getCustDetails("D:\\Test\\CustDetails.csv");
		for(Customer cust:custList){
			System.out.println(cust);
		}
	}

	private static List<Customer> getCustDetails(String file) {
		List<Customer> custList =new ArrayList<>();
		Path pathToFile = Paths.get(file);
		try(BufferedReader br = Files.newBufferedReader(pathToFile)){
			String row = br.readLine();
			while(row!=null){
				String [] attributes = row.split(",");
				Customer cust = getOneCustomer(attributes);
				custList.add(cust);
				row=br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return custList;
	}

	private static Customer getOneCustomer(String[] attributes) {
		int custId,pinCode;
		String custName,custCity,stateCode;
		try{
			custId = Integer.parseInt(attributes[0]);
		}
		catch(Exception e){custId = 0;}
		try{
			custName = attributes[1];
		}
		catch(Exception e){custName = null;}
		try{
			custCity = attributes[2].equalsIgnoreCase("")?null:attributes[2];
		}
		catch(Exception e){custCity = null;}
		try{
			pinCode = Integer.parseInt(attributes[3]);
		}
		catch(Exception e){pinCode = 0;}
		try{
			stateCode = attributes[4];
		}
		catch(Exception e){stateCode = null;}
		Customer cust = new Customer(custId,custName,custCity,pinCode,stateCode);
		
		return cust;
	}
}
