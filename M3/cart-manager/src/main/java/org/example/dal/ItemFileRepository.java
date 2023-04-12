package org.example.dal;

import org.example.models.Item;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
@Repository

public class ItemFileRepository implements ItemRepository {
    List<Item> items;
    private String fileName;

    public ItemFileRepository(@Value("./data.txt")String fileName) {
        items = new ArrayList<>();
        this.fileName = fileName;
        File file = new File(fileName);
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException ex){

            }
        }
    }

    private List<Item> loadItems() throws DALException {
        List<Item> items = new ArrayList<>();
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader reader = new BufferedReader(fileReader)) {

            // When there are no more lines, readLine() return null.
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] fields = line.split(",");
                Item item = new Item();
                item.setId(Integer.parseInt(fields[0]));
                item.setName(fields[1]);
                item.setPrice(Double.parseDouble(fields[2]));
                item.setQty(Integer.parseInt(fields[3]));
                items.add(item);
            }

        } catch (IOException ex) {
            throw new DALException("Unable to read file");
        }
        return items;
    }
    private void saveItems(List<Item> items) throws DALException {
        File file = new File(fileName);
        try{
            file.createNewFile();
        }catch (IOException ex){
            throw new DALException("Unable to create file");
        }

        PrintWriter writer = null;
        try{
            writer = new PrintWriter(fileName);
            for (Item item: items) {
                String line = String.format("%s,%s,%.2f,%d", item.getId(), item.getName(), item.getPrice(), item.getQty());
                writer.println(line);
            }
        }catch (FileNotFoundException ex){
            throw new DALException("Unable to write to file");
        }finally {
            if(writer != null){
                writer.close();
            }
        }
    }
    @Override
    public Item create(Item item) throws DALException {
        items = loadItems();
        int nextid = 0;
        for (Item i: items) {
            if(i.getId() > nextid){
                nextid = i.getId();
            }
        }
        nextid++;
        item.setId(nextid);
        items.add(item);
        saveItems(items);
        return item;
    }

    @Override
    public List<Item> readAll() throws DALException {
        items = loadItems();
        return items;
    }

    @Override
    public Item readById(int id) throws DALException {
        items = loadItems();
        for (Item item:items) {
            if(item.getId() == id){
                return item;
            }
        }
        return null;
    }

    @Override
    public void update(int id, Item item) throws DALException {
        items = loadItems();
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).getId() == id){
                items.set(i, item);
                break;
            }
        }
        saveItems(items);
    }

    @Override
    public void delete(int id) throws DALException {
        items = loadItems();
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).getId() == id){
                items.remove(i);
                break;
            }
        }
        saveItems(items);
    }
}
