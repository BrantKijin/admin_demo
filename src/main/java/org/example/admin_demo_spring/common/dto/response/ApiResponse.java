package org.example.admin_demo_spring.common.dto.response;

public record ApiResponse(
	ApiStatus status,
	String message,
	Object data
) {
	public static ApiResponse success(Object data) {
		return new ApiResponse(ApiStatus.SUCCESS, null, data);
	}

	public static ApiResponse error(String message) {
		return new ApiResponse(ApiStatus.ERROR, message, null);
	}
}
