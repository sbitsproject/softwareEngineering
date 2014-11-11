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
   public void setTitle(String Title) {
	   this.title = Title;
   }
   
   public String getDescription() {
        return description;
   }
   public void setDescription(String Sum){
	   this.description = Sum;
   }
   public String getFirst(){
	   return first;
   }
   public void setFirst(String First) {
	   this.first = First;
   }
   public String getLast() {
	   return last;
   }
   public void setLast(String Last) {
	   this.last = Last;
   }
   public String getEntity() {
	   return entity;
   }
   public void setEntity(String Entity) {
	   this.entity = Entity;
   }
   public String getEmail() {
	   return email;
   }
   public void setEmail(String Email) {
	   this.email = Email;
   }
   
}