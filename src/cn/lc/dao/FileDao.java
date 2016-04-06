package cn.lc.dao;

import java.util.List;
import java.util.Map;

import cn.lc.domain.UPFile;

public interface FileDao {
    public void delete(String id);
    public UPFile findByid(String id);
    public  List<UPFile> findByUser(String username);
    public void add(UPFile file);  
    public int getRecord();
    public int getRecord(String username);
    public  List<UPFile> getAllfile(int startIndex,int pageSize,String username);
    public List<UPFile> getAllfile(int startIndex,int pageSize);
    public List<UPFile> findFilebystate(boolean state);
    public void changeState(String id);
    public List<UPFile> getAllfile();
    public void updateFile(String id,Map<String, String> map);
}
