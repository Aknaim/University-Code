/* Syed Naim,1066208,L09*/

public class BVector{

	private int    		size;
	private boolean[]  bVectorData;	   //reference to a boolean array which stores the vector elements
	
	
	public BVector( int n )
	{       /* if n<=0 changes n to 1; it initializes the BVector object to represent the boolean vector with n elements, all set to true*/
            int i;
            
		if (n<=0){
                     n = 1;
                 
                }
                
                size = n;
                
                bVectorData = new boolean[n];
                
                for(i=0;i<n;i++){            
                    
                bVectorData[i] = true;                
                }
                
	}

	public BVector( boolean[] data )
	{       /* initializes the BVector object to represent the boolean vector data*/

		size = data.length;
		bVectorData = new boolean[size];
		//here copy the elements from array data to array bVectorData
		for( int i=0; i<size; i++ ){
			bVectorData[i] = data[i];
		}

	}
	
	public int getSize()
	{	/* returns the size of the boolean vector  */
		
		return this.size;

	}

	public boolean getElement( int i )
	{ 	/* returns the vector element at index i (indexing starts at 0); if i 
		is not a valid index, an IndexOutOfBoundsException is thrown, with message: Invalid index.*/
		
		return bVectorData[i];
                
	}


	public void setElement( boolean  x, int i )  
	{	/* sets to x the element at index i of this BVector object;  if i 
		is not a valid index, an IndexOutOfBoundsException is thrown, with message: Invalid index.*/
	
		this.bVectorData[i] = x;
                
	}
	
	public BVector logicalAnd(BVector v )
	{	/*if this BVector and v do not have the same number of elements the method throws an 
		ArithmeticException with message: Illegal operation;
		else creates a new BVector object which represents the component-wise logical AND
		 of this Bvector and v; returns a reference to the newly created object*/
	int i;
        BVector AND = new BVector(this.getSize());
		if (this.getSize() != v.getSize()){
                    
                    throw new ArithmeticException("Illegal operation");                
                }
                else{
                
                    
                    for (i=0;i<(this.getSize());i++){
                        AND.setElement((v.getElement(i) & this.getElement(i)),i);
                    
                    }
                }                                
             return AND;
	}

		
	public void logicalOr( BVector v)
	{	/*if this BVector and v do not have the same number of elements the method throws an 
		ArithmeticException with message: Illegal operation;
		 else it computes the component-wise logical OR between this BVector and v and stores the result in this 
		BVector*/
	
		int i;
		if (this.getSize() != v.getSize()){
                    
                    throw new ArithmeticException("Illegal operation");                
                }
                else{                
                    
                    for (i=0;i<(this.getSize());i++){
                        this.setElement((v.getElement(i) | this.getElement(i)),i);
                  
                    }
                }             
                
	}


	public String toString(  )
	{
		/* returns a string representing the boolean vector with consecutive elements separated by a blank space */
			
		String output = new String();
		
		for(int i=0; i < size; i++)
			output += " " + bVectorData[i];

		return output;	
		
	}
}//end class