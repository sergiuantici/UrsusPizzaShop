package pizzashop.repository;

import pizzashop.model.MenuData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MenuRepository {
    private static String filename = "data/menu.txt";
    private List<MenuData> listMenu;

    public MenuRepository(){
    }

    private void readMenu(){
        File file = new File(filename);
        this.listMenu= new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while((line=br.readLine())!=null){
                MenuData menuItem=getMenuItem(line);
                listMenu.add(menuItem);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MenuData getMenuItem(String line){
        MenuData item=null;
        if (line==null|| line.equals("")) return null;
        StringTokenizer st=new StringTokenizer(line, ",");
        String name= st.nextToken();
        double price = Double.parseDouble(st.nextToken());
        item = new MenuData(name, 0, price);
        return item;
    }

    public List<MenuData> getMenu(){
        readMenu();//create a new menu for each table, on request
        return listMenu;
    }
}