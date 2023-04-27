package Model;

import java.util.ArrayList;

public class Manager extends Person {

    private ArrayList<Request> requestQueue;

    private ManagerKey key;

    public Manager(int ID, String name, String email, String password) {
        super(ID, name, email, password);
        key = new ManagerKey(ID);
        RequestFactory requestFactory = new RequestFactory();
        requestQueue = requestFactory.createRequests();
    }

    public String keyValue() {
        return key.getKeyValue();
    }

    public ManagerKey getKey() {
        return key;
    }
    
    //get the request that status "pending"
    public ArrayList<Request> getPendingRequest(){
        ArrayList<Request> pendingRequests = new ArrayList<>();
        for(Request request : requestQueue){
            if(request.getStatus().equals("Pending")){
                pendingRequests.add(request);
            }
        }
        return pendingRequests;
    }

    //Approve the request
    public void approveRequest(Request request){
        request.approve();
        requestQueue.remove(request);
    }

    //Reject the request
    public void rejectRequest(Request request){
        request.reject();
    }
    
}
