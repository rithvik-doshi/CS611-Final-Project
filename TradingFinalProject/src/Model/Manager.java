package Model;

import java.util.ArrayList;

public class Manager extends Person {

    private final ArrayList<Request> requestQueue;

    private final ManagerKey key;

    public Manager(int ID, String name, String email, String password) {
        super(ID, name, email, password);
        key = new ManagerKey(ID);
        RequestFactory requestFactory = new RequestFactory();
        requestQueue = requestFactory.createRequests();
    }

    public ManagerKey getKey() {
        return key;
    }

    public void approveRequest(Request request){
        request.approve();
        requestQueue.remove(request);
    }

    //Reject the request
    public void rejectRequest(Request request){
        request.reject();
    }
    
}
