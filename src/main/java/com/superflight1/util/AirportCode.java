package com.superflight1.util;

public enum AirportCode {
    LAX("Los Angeles"),
    JFK("New York"),
    CDG("Paris"),
    HND("Tokyo"),
    DXB("Dubai"),
    ORD("Chicago"),
    ATL("Atlanta"),
    PEK("Beijing"),
    LHR("London"),
    FRA("Frankfurt"),
    AMS("Amsterdam"),
    DEL("New Delhi"),
    SIN("Singapore"),
    BKK("Bangkok"),
    SYD("Sydney"),
    GRU("Sao Paulo"),
    MEX("Mexico City"),
    YYZ("Toronto"),
    SFO("San Francisco"),
    MIA("Miami"),
    BCN("Barcelona"),
    IST("Istanbul"),
    HKG("Hong Kong"),
    MAD("Madrid"),
    DFW("Dallas/Fort Worth"),
    ICN("Seoul"),
    MUC("Munich"),
    ZRH("Zurich"),
    SEA("Seattle"),
    DEN("Denver"),
    MEL("Melbourne"),
    CPT("Cape Town"),
    JNB("Johannesburg"),
    YVR("Vancouver"),
    LGW("London Gatwick"),
    EZE("Buenos Aires"),
    HNL("Honolulu"),
    ANC("Anchorage"),
    KUL("Kuala Lumpur"),
    DOH("Doha"),
    AUH("Abu Dhabi"),
    VIE("Vienna"),
    WAW("Warsaw"),
    BUD("Budapest"),
    PRG("Prague"),
    HEL("Helsinki"),
    OSL("Oslo"),
    CPH("Copenhagen"),
    ARN("Stockholm");

    private final String city;
    private final String code;

    AirportCode(String city) {
        this.code = this.name();
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public String getCode() {
        return code;
    }
    }
