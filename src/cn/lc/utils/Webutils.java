package cn.lc.utils;

import java.util.UUID;

public class Webutils {
  public static String MakeId()
  {
	  return UUID.randomUUID().toString();
  }
}
