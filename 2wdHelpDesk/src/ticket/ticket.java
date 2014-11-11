package ticket;


import java.util.UUID;


public class ticket
{
	private String Id;
	private String title; 
	private String description;
	private String first;
	private String last;
	private String entity;
	private String email;
	
	public void newTicket(){
	  
	   String uuid = UUID.randomUUID().toString();
	    Id= uuid;
   }
   
   public String getTitle(){
	   return title;
   }
   
   public String getDescription() {
        return description;
   }
   public String getFirst(){
	   return first;
   }
   public String getLast() {
	   return last;
   }
   public String getEntity() {
	   return entity;
   }
   public String getEmail() {
	   return email;
   }
   
}