package com.company.patterns.proxyDb;

public class ProxyMain {
    public void main(String[] args){
        SqlManager sqlManagerForProxy = new SqlManager(new DbProxy());
        SqlManager sqlManagerForService = new SqlManager(new DbService());

        sqlManagerForProxy.getAllClients("user", "password", "request");
        sqlManagerForService.getAllClients("user1", "password1", "request1");
    }
}
