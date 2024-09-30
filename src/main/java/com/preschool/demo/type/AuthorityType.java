package com.preschool.demo.type;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum AuthorityType {
    SUPER_USER(Names.SUPER_USER),
    ADMIN(Names.ADMIN),
    USER(Names.USER),

    /**
     * MANAGE READ,WRITE,DELETE
     */
    MNG_USER(Names.MNG_USER),
    MNG_AUTHORITY(Names.MNG_AUTHORITY),
    MNG_ROLE(Names.MNG_ROLE),
    MNG_LANG(Names.MNG_LANG),
    MNG_COUNTRY_CITY_DISTRICT(Names.MNG_COUNTRY_CITY_DISTRICT),
    MNG_TRANSLATION(Names.MNG_TRANSLATION),
    MNG_SETTINGS(Names.MNG_SETTINGS),

    MNG_SLOT(Names.MNG_SLOT),
    MNG_ROOM(Names.MNG_ROOM),
    MNG_DEVICE(Names.MNG_DEVICE),
    MNG_ROOM_DEVICE(Names.MNG_ROOM_DEVICE),
    MNG_ACTION(Names.MNG_ACTION),

    EXTERNAL_API(Names.EXTERNAL_API),

    ;

    private String name;

    AuthorityType(String name) {
        this.name = name;
    }

    public static List<AuthorityType> getOnlySuperUserAuthoritiesAsList() {
        return Arrays.asList(SUPER_USER, MNG_LANG, MNG_COUNTRY_CITY_DISTRICT, MNG_TRANSLATION, MNG_AUTHORITY, MNG_SETTINGS);
    }

    public static Set<String> getOnlySuperUserSpecificAuthorityCodesAsSet() {
        return getOnlySuperUserAuthoritiesAsList().stream().map(AuthorityType::getName).collect(Collectors.toSet());
    }

    public static Set<String> getOnlyAdminSpecificAuthorityCodesAsSet() {
        return new HashSet<>(Collections.singletonList(ADMIN.getName()));
    }

    public static List<AuthorityType> getSuperUserAuthorities() {
        return Stream.of(AuthorityType.values()).collect(Collectors.toList());
    }

    public static List<AuthorityType> getAdminAuthorities() {
        return Stream.of(AuthorityType.values())
                .filter(m -> getOnlySuperUserSpecificAuthorityCodesAsSet().stream().noneMatch(e -> e.equalsIgnoreCase(m.name)))
                .collect(Collectors.toList());
    }

    public static List<AuthorityType> getDefaultAuthorities() {
        return Stream.of(USER)
                .collect(Collectors.toList());
    }


    public String getName() {
        return name;
    }

    public static class Names {
        public static final String SUPER_USER = "SUPER_USER";
        public static final String ADMIN = "ADMIN";
        public static final String USER = "USER";

        public static final String MNG_USER = "MNG_USER";
        public static final String MNG_ROLE = "MNG_ROLE";
        public static final String MNG_AUTHORITY = "MNG_AUTHORITY";
        public static final String MNG_LANG = "MNG_LANG";
        public static final String MNG_COUNTRY_CITY_DISTRICT = "MNG_COUNTRY_CITY_DISTRICT";
        public static final String MNG_TRANSLATION = "MNG_TRANSLATION";
        public static final String MNG_SETTINGS = "MNG_SETTINGS";

        public static final String MNG_SLOT = "MNG_SLOT";
        public static final String MNG_ROOM = "MNG_ROOM";
        public static final String MNG_DEVICE = "MNG_DEVICE";
        public static final String MNG_ROOM_DEVICE = "MNG_ROOM_DEVICE";
        public static final String MNG_ACTION = "MNG_ACTION";

        public static final String EXTERNAL_API = "EXTERNAL_API";

    }


}
