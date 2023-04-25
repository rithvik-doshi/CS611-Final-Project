package Model;

import java.util.ArrayList;

public class Manager extends Person {
    //singleton
    private static Manager manager = null;
    private ArrayList<Request> requestQueue;
    
    
    private Manager(String name, String password, String type, String id) {
        super(name, password, "M", "M001");
        //TODO Auto-generated constructor stub
    }


    public static Manager getInstance(){
        if(manager == null){
            manager = new Manager("admin", "admin", "M", "M001");
        }
        return manager;
    }

    //Approve the request
    public void approveRequest(Request request){
        request.approve();
    }

    //Reject the request
    public void rejectRequest(Request request){
        request.reject();
    }

    
    
}
