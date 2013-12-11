/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao.Help;

import java.util.ArrayList;

/**
 * from  http://www.javaprogrammingforums.com/java-programming-tutorials/696-multi-dimension-arraylist-example.html
 * @author ir
 */
public class ArrayList2D {
    
	ArrayList<ArrayList<Object>> array;
 
	public ArrayList2D()
	{
		array = new ArrayList<>();
	}
 
	/**
	 * ensures a minimum capacity of num rows. Note that this does not guarantee
	 * that there are that many rows.
	 * 
	 * @param num
	 */
	public void ensureSize(int num)
	{
		array.ensureCapacity(num);
                while (array.size() < num) {
                         array.add(null);
                }
                System.out.println("teste d1111ada " + getNumRows());
  	}
 
	/**
	 * Ensures that the given row has at least the given capacity. Note that
	 * this method will also ensure that getNumRows() >= row
	 * 
	 * @param row
	 * @param num
	 */
	public void ensureCapacity(int row, int num)
	{
		ensureSize(row);
                System.out.println("teste dada " + getNumRows());
		while (row < getNumRows())
		{
			array.add(new ArrayList<>());
		}
	//	array.get(row).ensureCapacity(num);
	}
 
	/**
	 * Adds an item at the end of the specified row. This will guarantee that at least row rows exist.
	 */
	public void Add(Object data, int row)
	{
		ensureSize(row);
		while(row >= getNumRows())
		{
			array.add(new ArrayList<>());
		}
		array.get(row).add(data);
	}
 
        
	public Object get(int row, int col)
	{
		return array.get(row).get(col);
	}
 
        
	public void set(int row, int col, Object data)
	{
		array.get(row).set(col,data);
	}
 
	public void remove(int row, int col)
	{
		array.get(row).remove(col);
	}
 
        
	public boolean contains(Object data)
	{
		for (int i = 0; i < array.size(); i++)
		{
			if (array.get(i).contains(data))
			{
				return true;
			}
		}
		return false;
	}
 
        
	public int getNumRows()
	{
		return array.size();
	}
 
        
	public int getNumCols(int row)
	{
		return array.get(row).size();
	}
}
   
    
