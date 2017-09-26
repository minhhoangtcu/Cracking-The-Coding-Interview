package selfstudy;

import java.util.*;

public class Solution {
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
    int highestScoreListing = 0;
    int numListing = 0;
    
    Listing[] listings = new Listing[results.length];
    Set<Integer> selectedListingIndexes = new HashSet<>();
    for (int i = 0; i < results.length; i++) {
      listings[i] = new Listing(results[i]);
    }
    
    while (numListing < listings.length) {
      int uniqueHostFinder = highestScoreListing;
      int firstUnused = listings.length;
      Set<Integer> selectedHostID = new HashSet<>();
      
      while (uniqueHostFinder < listings.length && selectedHostID.size() < num) {
        if (selectedListingIndexes.contains(uniqueHostFinder)) {
          uniqueHostFinder++;
          continue;
        }
        
        Listing listing = listings[uniqueHostFinder];
        if (!selectedHostID.contains(listing.getHostID())) {
          selectedHostID.add(listing.getHostID());
          selectedListingIndexes.add(uniqueHostFinder);
          paginatedResults.add(listing.getOriginalFormat());
          numListing++;
        } else {
          firstUnused = Math.min(firstUnused, uniqueHostFinder);
        }
        
        uniqueHostFinder++;
      }
      
      if (firstUnused == listings.length) {
        firstUnused = uniqueHostFinder;
      }
      
      int numListingRemainingForPage = num - selectedHostID.size(); 
      if (numListingRemainingForPage > 0) {
        // Pad remaining with any unselected listing.
        int hostFinder = firstUnused;
        while (hostFinder < listings.length && numListingRemainingForPage > 0) {
          if (selectedListingIndexes.contains(hostFinder)) {
            hostFinder++;
            continue;
          }
          
          Listing listing = listings[hostFinder];
          paginatedResults.add(listing.getOriginalFormat());
          numListingRemainingForPage--;
          selectedListingIndexes.add(hostFinder);
          
          numListing++;
          hostFinder++;
        }
        
        firstUnused = hostFinder;
      }
      
      if (numListing != listings.length) {
        paginatedResults.add(" ");
      }
      highestScoreListing = firstUnused;
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
