package com.zappos.neeraj.ilovezappos;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Neeraj on 2/5/2017.
 */

public class SearchResult implements Serializable {
    String originalTerm,currentResultCount,totalResultCount,term,statusCode;
    List<Product> results;
}
