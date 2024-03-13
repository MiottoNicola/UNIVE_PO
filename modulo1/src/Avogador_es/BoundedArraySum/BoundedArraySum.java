package Avogador_es.BoundedArraySum;

class SumTooSmallException extends Exception {

	public SumTooSmallException(int sum) {
		super("Sum is too small: " + sum);
	}
}

class ArraySum {
    private static void throwIfNecessary(int[] array, int maxIndex, int requiredValue) throws SumTooSmallException {
        if(maxIndex<0 || maxIndex>=array.length)
            throw new ArrayIndexOutOfBoundsException(maxIndex);
        int sum=0;
        for(int i=0; i<=maxIndex; i++)
            sum+=array[i];
        if(sum<requiredValue)
            throw new SumTooSmallException(sum);
    }
    
    public static void check(int[] array, int maxIndex, int requiredValue){
        try {
            throwIfNecessary(array, maxIndex, requiredValue);
        } catch (SumTooSmallException e){
            System.out.println("SumTooSmallException: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("ArrayIndexOutOfBoundsException: " + e.getMessage());
        } finally {
            System.out.println("No exception");
        }
    }
}