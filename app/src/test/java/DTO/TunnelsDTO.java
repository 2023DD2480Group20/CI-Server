package DTO;

import java.util.ArrayList;

public class TunnelsDTO {

    public ArrayList<Tunnel> tunnels;

    public class Tunnel{
        public String name;
        public String public_url;
        public String proto;
    }
}

