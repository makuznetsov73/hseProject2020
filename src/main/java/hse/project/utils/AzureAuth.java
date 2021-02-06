package hse.project.utils;

import com.microsoft.azure.management.Azure;

public class AzureAuth {
	
	private static Azure azure;
	
	public static void setAzure(Azure azure) {
		AzureAuth.azure = azure;
	}
	
	public static Azure getAzure() {
		return azure;
	}
	
}
