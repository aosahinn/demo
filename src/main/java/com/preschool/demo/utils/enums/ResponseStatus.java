package com.preschool.demo.utils.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.StringUtils;

import java.util.stream.Stream;

public enum ResponseStatus {

    SUCCESS("S00"),

    LOGIN_ERROR("E01"),

    REFRESH_TOKEN_INVALID("E02"), // incorrect refresh token

    INVALID_ACCESS_TOKEN("E03"), // incorrect access token

    OPTIMISTIC_LOCKING("E04"), // too many consecutive requests to modify a resource caused resource modification to be locked

    AKSIGORTA_CONNECTION_ERROR("E05"), // aksigorta services respond with server error

    RESOURCE_NOT_FOUND("E06"), // generic resource not found error

    BAD_REQUEST("E07"), // generic bad request error

    AGENCY_NOT_FOUND("E08"),

    MESSAGE_NOT_FOUND("E10"),

    VALIDATION_ERROR("E11"),

    POLICY_NOT_FOUND("E12"),

    ACCESS_TOKEN_EXPIRED("E13"),

    CLAIMS_NOT_FOUND("E14"),

    // HIERARCHY_NOT_FOUND("E15"), --> deprecated

    UNAUTHORIZED_READ_MESSAGE("E16"), // user is not authorized to read message

    UNAUTHORIZED_SEND_MESSAGE("E17"), // user is not authorized to send message to an agency in recipient list

    LOGIN_NOT_SECURE("E18"), // user login is not secure due to security table

    PROFILE_NOT_FOUND("E19"),

    RENEW_SUMMARY_NOT_FOUND("E20"),

    COMMISSION_NOT_FOUND("E21"),

    UNRENEWED_POLICIES_NOT_FOUND("E22"),

    RECORD_NOT_FOUND("E23"),

    TIMELINE_NOT_FOUND("E24"),

    CAMPAIGN_NOT_FOUND("E25"),

    USER_LEVEL_NOT_FOUND("E26"),

    INVALID_DATA("E27"), // aksigorta or nexum services returned invalid data

    DAMAGE_NOT_FOUND("E28"),

    CLIENT_ID_NOT_FOUND("E29"),

    RENEWAL_GENERIC_INFO("E30"),

    COLLECTION_NOT_FOUND("E31"),

    INVALID_CAPTCHA("E32"),

    CAPTCHA_REQUIRED("E33"),



    // new codes

    SAT_LOGIN_ERROR("E1000"),
    INTERNAL_USER_TITLE_NOT_ALLOWED_ERROR("E1001"),
    ROLE_SECURITY_NOT_FOUND("E1002"),
    HIERARCHY_TITLE_NOT_FOUND("E1003"),
    HIERARCHY_NOT_FOUND("E1004"),
    SAT_TOKEN_LOGIN_ERROR("E1005"),
    LDAP_ERROR("E1006"),


    ;

    private final String value;

    ResponseStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }

    public static ResponseStatus fromValue(String val) {
        return Stream.of(ResponseStatus.values())
                .filter(s -> StringUtils.equalsIgnoreCase(val, s.value))
                .findAny()
                .orElse(ResponseStatus.BAD_REQUEST);
    }
}
