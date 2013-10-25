import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class ZerplyAssignment_Template {
	static String email;
	static String hashed;
	static String firstName;
	static String lastName;
	static String twitter;
	static String github;
	static String website;
	static String university;
	static String major;
	static String company;
	static String title;
	static String phone;
	static String password1, password2;
	public static void main(String[] args) throws IOException{
		//calls the methods below
		userMenu();
	}
	

	//this method AT LEAST welcomes the user and takes the initial user input
	static String[] UserInfo;
	static final String profileFile="info.csv";
	static final String fileName="user.csv";
	static void userMenu() throws IOException
	{ 
		
		
		System.out.println("welcome. Are you a new user? (y/n)");
		String response=getUserInput();
		//what happens when you are a new user, take particulars and validat all info
		if(response.equalsIgnoreCase("y"))
		{	System.out.println("Enter first name");
			firstName=getUserInput();
			boolean validFirstName=validateFirstName();
			System.out.println("Enter last name");
			lastName=getUserInput();
			boolean validLastName=validateLastName();
			System.out.println("Enter password ");
			password1=getUserInput();
			System.out.println("Please confirm your password ");
			password2=getUserInput();
			boolean validPassword=validatePassword(password1,password2);
			System.out.println("Enter email");
			email=getUserInput();
			boolean validEmail=validateEmail();
			//this method reads existing data from user information and users files
			 UserInfo=readInputFile(fileName);
			//boolean checkEmail=(!checkExistingUser (email, UserInfo));
			while(true)
			{
				if(validEmail&&validLastName&&validPassword&&validFirstName&&(!checkExistingUser (email, UserInfo)))
				{	hashed = BCrypt.hashpw(password1, BCrypt.gensalt());
					String record=storeUser(email, hashed, firstName,  lastName);
					
					outputInfo(record,fileName);
					//this method writes a String with user info to the users.csv or info.csv (make sure you don’t overwrite your info!)
					
					//boolean usrLogin=userLogIn ( email, password1,  UserInfo);
					//this method asks the user for profile info and then stores it in a comma separated String returns string
					//storeUserInfo () ;
					//this method asks the user for profile info and then stores it in a comma separated String
					//static String storeUserInfo 
					
							
					//this method takes a user provided email and the info.csv file object, pulls the profile info of the user with the provided email
					//printUserProfile ("info.csv", email);
					//this method writes a String with user info to the users.csv or info.csv (make sure you don’t overwrite your info!)
					//outputInfo (String record, String fileName)
					
					
					break;
				}
				else
				{
					
					while(checkExistingUser(email, UserInfo))
					{	System.out.println("your email has been taken by someone already please enter any email");
						email=getUserInput();
						validEmail=validateEmail();
						
					}
					continue;
					
				}
			}
			
			//this method takes user provided email, password, and user info and checks it against the data from the user.csv file
			//stores twitter info , github that is profile information
			String profile=storeUserInfo ();
			//writes information like twitter handle on info.csv
			//static void outputInfo (String record, String fileName)
			outputInfo(profile,profileFile);
			//have to display now
			//prints all about user
			//this method reads existing data from user information and users files
			 //UserInfo= readInputFile (profileFile);
			//printUserprofile
			 System.out.println("thanks for providing information see you some other time");
				
			
			
		}
		//What happens when you are not a new user
		else if(response.equalsIgnoreCase("n"))
		{
			alreadyExistingUser();
			
			
		}
		//when you type in something other than y or n
		else{
			userMenu();
		}
	}
	
	// already Existing User login
	static void alreadyExistingUser() throws IOException{
		System.out.println("Please enter email address");
		email=getUserInput();
		boolean validEmail=validateEmail();
		
		System.out.println("Please enter your password");
		password1=getUserInput();
	//this method takes user provided email, password, and user info and checks it against the data from the user.csv file
		
		//this method reads existing data from user information and users files
		//String [] readInput=readInputFile (fileName);
		UserInfo=readInputFile(fileName);
		boolean answer=userLogIn ( email, password1,  UserInfo);
			if(answer)
			{
				
			//System.out.println("you are logged in");
			printUserProfile(profileFile,email);
			
			}
			else
			{
				System.out.println("check if password or user name is typed correctly");
				System.out.println("do want to try again (y/n)");
				String response1=getUserInput();
				
				if(response1.equalsIgnoreCase("y"))
				{
					alreadyExistingUser();
					
				}
				else
				{	System.out.println("Bye for now");
					System.exit(0);
				}
				
			}
	}
		
	//validates first name
	static boolean validateFirstName() throws IOException
	{
		if((firstName.matches("[a-zA-Z]+")))
		{
			return true;
			
		}
		else
		{
		
			while(!(firstName.matches("[a-zA-Z]+")))
			{
				
					System.out.println("Enter first name again in the right format");
					firstName=getUserInput();
					continue;
				
				
				
			}
		return true;
		}
		
	}
	// validates last name
	static boolean validateLastName() throws IOException
	{
		if((lastName.matches("[a-zA-Z]+"))){
			return true;
			
		}else{
		
		while(!(lastName.matches("[a-zA-Z]+")))
		{
			
				System.out.println("Enter last name again in the right format");
				lastName=getUserInput();
				continue;
			
			
			
		}
		return true;
		}
		
	}
	// validates email in this format ([lower alpha/num/.]@[loweralpha/num].[2 or 3 lower alpha])
	static boolean validateEmail() throws IOException
	{
		if((email.matches("^[a-z0-9]+[a-z0-9_.]+[@][a-z0-9]+[_.][a-z]{2,3}"))){
			return true;
			
		}else{
			//([lower alpha/num/.]@[loweralpha/num].[2 or 3 lower alpha])
		while(!(email.matches("^[a-z0-9]+[a-z0-9_.]+[@][a-z0-9]+[_.][a-z]{2,3}")))
		{
			
				System.out.println("Enter email again in the right format");
				email=getUserInput();
				continue;
			
			
			
		}
		return true;
		}
		
	}
		
	//my own function for getting input from user
	static String getUserInput()throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String	response = in.readLine();
		return response;
	}

	//this method reads existing data from user information and users files
	static String [] readInputFile (String filename) throws FileNotFoundException 
	{ 
		String [] records = new String [5];
		String temp="";
		File file=new File(filename);
		Scanner scanner = new Scanner(file);
		 
	          
		while(scanner.hasNextLine())
		{
			temp+=scanner.nextLine()+";";
		}
			records=temp.split(";");
		return records;
	}
	

	//this method checks to see if the user logging in exists in the input file already (make sure you check for cases where users.csv is empty!)
	static boolean checkExistingUser (String email, String [] userInfo) { 
		String[] temp=new String[3];
		for(String user:userInfo)
		{	temp=user.split(",");
			for(String name:temp)
			{
				if(email.equals(name)){
					return true;
				}else{
					continue;
				}
			}
			continue;
			
		}
		return false;
	}

	//this method takes user input for new users and stores it in a comma separated String
	static String storeUser (String email, String password, String firstName,  String lastName) 
	{ 
			String particulars=email+","+password+","+firstName+","+lastName+"\n";
			return particulars;
	}

	//this method writes a String with user info to the users.csv or info.csv (make sure you don’t overwrite your info!)
	static void outputInfo (String record, String fName)
	{
		try {
			PrintWriter p= new PrintWriter(new BufferedWriter(new FileWriter(fName,true)));
			p.write(record);
			p.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

	//this method takes user provided email, password, and user info and checks it against the data from the user.csv file
	//BCrypt.checkpw(candidate, hashed)  //storage[1].equals(password)
	static boolean userLogIn (String email, String password, String [] userInfo) 
	{
		String[] storage=new String[4];
		String[] store=new String[4];
		for(String info : userInfo)
		{
			storage=info.split(",");
			
				if(storage[0].equals(email)&&BCrypt.checkpw(password,storage[1])){
					return true;
				}else{continue;}
			
			
			
		}
		
			
		
		return false;
	}

	//this method takes a user provided email and the info.csv file object, pulls the profile info of the user with the provided email
	static void printUserProfile (String fName, String email) throws FileNotFoundException 
	{
		Scanner scanner= new Scanner(new File(fName));
		String temp="";
		String[] check=new String[3];
		while(scanner.hasNextLine())
		{
			temp=scanner.nextLine();
			check=temp.split(",");
			if(check[0].equals(email))
			{
				System.out.println("                          you are successfully logged in           ");
				System.out.println("---------------------------------------------------------------------------");
				System.out.println("twitter     :           	"+check[1]);
				System.out.println("git         :       		"+check[2]);
				System.out.println("web         :    		"+check[3]);
				System.out.println("university  :           	"+check[4]);
				System.out.println("major       :     		"+check[5]);
				System.out.println("company     :       		"+check[6]);
				System.out.println("title       :    		"+check[7]);
				System.out.println("phone       :     		"+check[8]);
			}else{
				continue;
			}
		}
		scanner.close();
		
		
	}

	//this method asks the user for profile info and then stores it in a comma separated String
	static String storeUserInfo () throws IOException{
		//takes twitter handle
		String result;
		System.out.println("Please enter your Twitter handle");
		twitter=getUserInput();
		boolean validTwitter=validateTwitter();
		//takes github user name 
		System.out.println("Please enter your Github user name");
		github=getUserInput();
		boolean validGithub=validateGithub();
		//takes website address and validates
		System.out.println("Please enter your website");
		website=getUserInput();
		boolean validWebsite=validateWebsite();
		//takes name of university
		System.out.println("Please enter name of your University");
		university=getUserInput();
		boolean validUniversity=validateUniversity();
		System.out.println("Please enter your major");
		major=getUserInput();
		boolean validMajor=validateMajor();
		System.out.println("Please enter your company name");
		company=getUserInput();
		boolean validCompany=validateCompany();
		System.out.println("enter your title");
		title=getUserInput();
		boolean validTitle=validateTitle();
		System.out.println("enter your phone number");
		phone=getUserInput();
		boolean validPhone=validatePhone();
		
			return email+","+twitter+","+github+","+website+","+university+","+major+","+company+","+title+","+phone+"\n";
		
		
		
	}
	///1. Twitter handle (alpha/num)
	
	static boolean validateTwitter() throws IOException{

		if((twitter.matches("[a-zA-Z0-9]+")))
		{
			return true;
			
		}
		else
		{
		
			while(!(twitter.matches("[a-zA-Z0-9]+")))
			{
				
					System.out.println("Enter twitter handle again in the right format");
					twitter=getUserInput();
					continue;
				
				
				
			}
		return true;
		}
		
		
	}
		//3. Website name (www.[lower alpha/num/.].[2 or 3 lower alpha])
		static boolean validateWebsite()throws IOException{

		if((website.matches("[w]{3}\\.[a-z0-9]{1,}\\.[a-z]{2,3}")))
		{
			return true;
			
		}
		else
		{
		
			while(!(website.matches("[w]{3}\\.[a-z0-9]{1,}\\.[a-z]{2,3}")))
			{
				
					System.out.println("Enter website address again in the right format");
					website=getUserInput();
					continue;
				
				
				
			}
		return true;
		}
		
	}
		//2. GitHub username (alpha/num)
		static boolean validateGithub()throws IOException{

		if((github.matches("[a-zA-Z0-9]+")))
		{
			return true;
			
		}
		else
		{
		
			while(!(github.matches("[a-zA-Z0-9]+")))
			{
				
					System.out.println("Enter first name again in the right format");
					github=getUserInput();
					continue;
				
				
				
			}
		return true;
		}
		
	}
		//4. University name (alpha/num/blank spaces)
		static boolean validateUniversity() throws IOException
		{
			if((university.matches("[a-zA-Z0-9\\s]+")))
			{
				return true;
				
			}
			else
			{
			
				while(!(university.matches("[a-zA-Z0-9\\s]+")))
				{
					
						System.out.println("Enter the university you attended again in the right format");
						university=getUserInput();
						continue;
					
					
					
				}
			return true;
			}
	}
		//5. Major name (alpha/num/blank spaces)
	static boolean validateMajor() throws IOException{
		if((major.matches("[a-zA-Z0-9\\s]+")))
		{
			return true;
			
		}
		else
		{
		
			while(!(major.matches("[a-zA-Z0-9\\s]+")))
			{
				
					System.out.println("Enter major again in the right format");
					major=getUserInput();
					continue;
				
				
				
			}
		return true;
		}
	}
		//6. Current company (alpha/num/blank spaces)
	static boolean validateCompany() throws IOException{
		if((company.matches("[a-zA-Z0-9\\s]+")))
		{
			return true;
			
		}
		else
		{
		
			while(!(company.matches("[a-zA-Z0-9\\s]+")))
			{
				
					System.out.println("Enter company name again in the right format");
					company=getUserInput();
					continue;
				
				
				
			}
		return true;
		}
	}
	//7. Current title (alpha/num/blank spaces)
	static boolean validateTitle() throws IOException{
		if((title.matches("[a-zA-Z0-9\\s]+")))
		{
			return true;
			
		}
		else
		{
		
			while(!(title.matches("[a-zA-Z0-9\\s]+")))
			{
				
					System.out.println("Enter your title again in the right format");
					title=getUserInput();
					continue;
				
				
				
			}
		return true;
		}
	}
	//8. Phone number (10 digits with dashes)
	static boolean validatePhone() throws IOException{
		if((phone.matches("[0-9]{3}[-][0-9]{3}[-][0-9]{4}")))
		{
			return true;
			
		}
		else
		{
		
			while(!(phone.matches("[0-9]{3}[-][0-9]{3}[-][0-9]{4}")))
			{
				
					System.out.println("Enter phone number  again in the right format");
					phone=getUserInput();
					continue;
				
				
				
			}
		return true;
		}
	}

	// *** HELPER METHODS ***

	//this method checks if the two user entered passwords are the same
	static boolean validatePassword (String password1, String password2) throws IOException { 
		//hashed = By.hashpw(password1, By.gensalt());
		//BCrypt.checkpw(password1, hashed)
		//password1.equals(password2)
		while(true)
		{
			if(password1.equals(password2))
			{
				return true;
			}
			else{
				System.out.println("Erro you entered password that do not match");
				System.out.println("Enter password ");
				password1=getUserInput();
				System.out.println("Please confirm your password");
				password2=getUserInput();
				continue;
			}
		}
	}

}
