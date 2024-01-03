package br.com.fiap.tech.challenge.enterprise.error;

import br.com.fiap.tech.challenge.exception.error.BaseApplicationError;
import br.com.fiap.tech.challenge.exception.error.ErrorType;

import static br.com.fiap.tech.challenge.exception.error.ErrorType.INTERNAL_SERVER_ERROR;
import static br.com.fiap.tech.challenge.exception.error.ErrorType.INVALID_PARAMETER;
import static br.com.fiap.tech.challenge.exception.error.ErrorType.NOT_FOUND;
import static java.lang.Boolean.TRUE;

public enum ApplicationError implements BaseApplicationError {

    UNKNOWN_ERROR("AE-001", INTERNAL_SERVER_ERROR, TRUE, "Unexpected error [{}]"),
    PRODUCT_NOT_FOUND_BY_UUID("AE-002", INVALID_PARAMETER, TRUE, "Product not found [uuid={}]"),
    CUSTOMER_NOT_FOUND_BY_UUID("AE-003", NOT_FOUND, TRUE, "Customer not found [uuid={}]"),
    CART_NOT_FOUND_BY_UUID("AE-007", INVALID_PARAMETER, TRUE, "Cart not found [uuid={}]"),
    CART_ITEM_NOT_AVAILABLE("AE-008", INVALID_PARAMETER, TRUE, "Cart item not available [cartUuid={} cartItemUuid={}]"),
    CART_CUSTOMER_NOT_AVAILABLE("AE-009", INVALID_PARAMETER, TRUE, "Cart customer not available [customerUuid={}]"),
    ;

    private final String code;

    private final ErrorType errorType;

    private final boolean acceptParameters;

    private final String description;

    ApplicationError(String code, ErrorType errorType, boolean acceptParameters, String description) {
        this.code = code;
        this.errorType = errorType;
        this.acceptParameters = acceptParameters;
        this.description = description;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public ErrorType getErrorType() {
        return errorType;
    }

    @Override
    public boolean getAcceptParameters() {
        return acceptParameters;
    }

    @Override
    public String getDescription() {
        return description;
    }
}