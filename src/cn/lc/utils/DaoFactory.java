package cn.lc.utils;

public class DaoFactory {
  private static DaoFactory daofartory=new DaoFactory();
  public static DaoFactory getInstance(){
	  return  daofartory;
  }
  
  public <T> T getDao(String className,Class<T> clazz)
  {
	 
try {
	 T t=(T) Class.forName(className).newInstance();
	 return t;
} catch (Exception e) {
	throw new RuntimeException(e);
}
  }
}
