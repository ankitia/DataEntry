package com.ia.Dao;

import java.util.List;

import com.ia.modal.DataEntry;
import com.ia.modal.MasterDataEntryURL;


public interface DataEntryDao {
	
	public int insertDataEntry(DataEntry dataEntry);
	
	List<MasterDataEntryURL> getDataEntryUrlList(int userId,String action);
	
	List<DataEntry> getDataEntry(int userId);
	List<DataEntry> getDataEntryByUrlId(int urlId);
		
	List<MasterDataEntryURL> exportMasterDataEntryUrlList();	 
}

