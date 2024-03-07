package com.kdu.smarthome.util;


@SuppressWarnings("all")
public enum ExceptionType {
    BadLoginCredentialsException,
    BadRequestException,
    NoUserFoundException,
    ResourceNotFoundException,
    InvalidHouseIdException,
    ExpiredToken,
    InvalidSignature,
    InvalidTokenFormat,
    InvalidAudience,
    InvalidIssuer,
    InvalidTokenClaim,
    TokenNotPresent,
    UnsupportedToken,
    RevokedToken,

    SavingHouseException,

    NoUserWithSpecifiedRoleException,
    InvalidDeviceException, InvalidDeviceCredentialException, DeviceNotRegisteredException, NoRoomInHouseException, InvalidUserProvidedToken, AuthenticationContextNullException

}
