package org.jmeter.reporting.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoadTest {

    @Id
    @ObjectId
    private String key;

    @JsonProperty(value = "ltKey")
    @NotNull
    @Valid
    private LoadTestKey loadTestKey;

    private int status;

    private boolean reference;

}
