package at.ac.tuwien.service;

import at.ac.tuwien.exception.NotAuthorizedException;
import at.ac.tuwien.exception.PathNotFoundException;
import at.ac.tuwien.exception.UsageException;

import java.io.UnsupportedEncodingException;

public interface ApiService {
    String handle(String path, String request) throws PathNotFoundException, NotAuthorizedException, UsageException, UnsupportedEncodingException;
}
