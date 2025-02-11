package com.example.webServer.utils;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.PrefixQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.util.ObjectBuilder;

import java.util.function.Function;

public class ProductQueryBuilder {

    public static Function<Query.Builder, ObjectBuilder<Query>> buildQuery(String searchParam, int minPrice, int maxPrice, int quantity) {

        BoolQuery.Builder boolQuery = new BoolQuery.Builder();

        // Prefix Query for product name
        if (searchParam != null && !searchParam.isEmpty()) {
            boolQuery.must(Query.of(q -> q
                    .prefix(PrefixQuery.of(p -> p
                            .field("productName.keyword")
                            .value(searchParam.toLowerCase())
                    ))
            ));
        };

//         Range Query for price
//        if (minPrice > 0 && maxPrice > 0) {
//            boolQuery.must(Query.of(q -> q
//                    .range(RangeQuery.of(r -> r
//                            .field("price")
//                            .gte((double) minPrice)
//                            .lte((double) maxPrice)
//                    ))
//            ));
//        } else if (minPrice > 0) {
//            boolQuery.must(Query.of(q -> q
//                    .range(RangeQuery.of(r -> r
//                            .field("price")
//                            .gte((double) minPrice)
//                    ))
//            ));
//        } else if (maxPrice > 0) {
//            boolQuery.must(Query.of(q -> q
//                    .range(RangeQuery.of(r -> r
//                            .field("price")
//                            .lte((double) maxPrice)
//                    ))
//            ));
//        }

//        // Term Query for quantity
//        if (quantity > 0) {
//            boolQuery.must(Query.of(q -> q
//                    .term(TermQuery.of(t -> t
//                            .field("quantity")
//                            .value(quantity)
//                    ))
//            ));
//        }

        return q -> q.bool(boolQuery.build());
    }
}