package com.practice.dontcallme.config.security;

import com.practice.dontcallme.backend.User;

@FunctionalInterface
public interface CurrentUser {
	User getUser();
}
