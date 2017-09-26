package selfstudy;

import java.util.*;

public class Solution2 {
  public static void main(String[] args) {
    String[] results = {
        "1,28,300.6,San Francisco",
        "4,5,209.1,San Francisco",
        "20,7,203.4,Oakland",
        "6,8,202.9,San Francisco",
        "6,10,199.8,San Francisco",
        "1,16,190.5,San Francisco",
        "6,29,185.3,San Francisco",
        "7,20,180.0,Oakland",
        "6,21,162.2,San Francisco",
        "2,18,161.7,San Jose",
        "2,30,149.8,San Jose",
        "3,76,146.7,San Francisco",
        "2,14,141.8,San Jose"
    };
    
    for (String s: paginate(5, results)) {
      System.out.println(s);
    }
  }
  
  static String[] paginate(int num, String[] results) {
    List<String> paginatedResults = new LinkedList<>();
    
    List<Listing> listings = new LinkedList<>();
    for (String s: results) {
      listings.add(new Listing(s));
    }
    
    while (!listings.isEmpty()) {
      Set<Integer> selectedHostID = new HashSet<>();
      
      Iterator<Listing> uniqueListingFinder = listings.iterator();
      while (uniqueListingFinder.hasNext() && selectedHostID.size() < num) {
        Listing listing = uniqueListingFinder.next();
        if (!selectedHostID.contains(listing.getHostID())) {
          selectedHostID.add(listing.getHostID());
          paginatedResults.add(listing.getOriginalFormat());
          uniqueListingFinder.remove();
        }
      }
      
      int numListingRemainingForPage = num - selectedHostID.size(); 
      if (numListingRemainingForPage > 0) {
        // Pad remaining with any unselected listing.
        Iterator<Listing> listingFinder = listings.iterator();
        while (listingFinder.hasNext() && numListingRemainingForPage > 0) {          
          Listing listing = listingFinder.next();
          paginatedResults.add(listing.getOriginalFormat());
          listingFinder.remove();
          numListingRemainingForPage--;
        }
      }
      
      if (!listings.isEmpty()) {
        paginatedResults.add(" ");
      }
    }
    
    return paginatedResults.toArray(new String[paginatedResults.size()]);
  }
  
  static class Listing {
    private int hostID;
    private String originalFormat;

    public Listing(String rawCSVText) {
      originalFormat = rawCSVText;
      setHostID(Integer.parseInt(rawCSVText.split(",")[0]));
    }

    public int getHostID() {
      return hostID;
    }

    public void setHostID(int hostID) {
      this.hostID = hostID;
    }
    
    public String getOriginalFormat() {
      return originalFormat;
    }

    public void setOriginalFormat(String originalFormat) {
      this.originalFormat = originalFormat;
    }
  }
}
