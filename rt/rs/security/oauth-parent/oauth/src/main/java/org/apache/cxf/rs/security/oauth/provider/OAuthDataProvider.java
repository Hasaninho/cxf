/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.cxf.rs.security.oauth.provider;

import java.util.List;

import org.apache.cxf.rs.security.oauth.data.AccessToken;
import org.apache.cxf.rs.security.oauth.data.Client;
import org.apache.cxf.rs.security.oauth.data.OAuthPermission;
import org.apache.cxf.rs.security.oauth.data.RequestToken;
import org.apache.cxf.rs.security.oauth.data.RequestTokenRegistration;

/**
 * OAuth provider responsible for persisting the information about 
 * OAuth consumers, request and access tokens.
 */
public interface OAuthDataProvider {

    /**
     * Returns the previously registered third-party {@link Client} 
     * @param clientId the client id
     * @return Client
     * @throws OAuthServiceException
     */
    Client getClient(String clientId) throws OAuthServiceException;

    /**
     * Creates a temporarily request token which will capture the
     * information about the {@link Client} attempting to access or
     * modify the resource owner's resource 
     * @param reg RequestTokenRegistration
     * @return new request token
     * @see RequestTokenRegistration
     * @throws OAuthServiceException
     */
    RequestToken createRequestToken(RequestTokenRegistration reg) throws OAuthServiceException;

    /**
     * Returns the previously registered {@link RequestToken}
     * @param requestToken the token key
     * @return RequestToken
     * @throws OAuthServiceException
     */
    RequestToken getRequestToken(String requestToken) throws OAuthServiceException;

    /**
     * Sets the verifier confirming the resource owner's agreement for
     * the {@link Client} to perform the action as represented by
     * the provided {@link RequestToken}. The runtime will report
     * this verifier to the client who will exchange it for 
     * a new {@link AccessToken}
     *    
     * @param requestToken the request token
     * @return the generated verifier
     * @throws OAuthServiceException
     */
    String setRequestTokenVerifier(RequestToken requestToken) throws OAuthServiceException;
    
    /**
     * Creates a new {@link AccessToken}
     * @param requestToken the request token approved by the resource owner
     * @return new AccessToken
     * @throws OAuthServiceException
     */
    AccessToken createAccessToken(RequestToken requestToken) throws OAuthServiceException;

    /**
     * Returns the {@link AccessToken}
     * @param accessToken the token key 
     * @return AccessToken
     * @throws OAuthServiceException
     */
    AccessToken getAccessToken(String accessToken) throws OAuthServiceException;

    /**
     * Removes the tokens associated with a given client id
     * @param clientId the client id
     * @throws OAuthServiceException
     */
    void removeTokens(String clientId) throws OAuthServiceException;;

    /**
     * Returns the list of {@link OAuthPermission} beans describing opaque
     * permissions (aka scopes) such as "read_data", etc
     * @param requestPermissions the list of opaque scopes/permissions 
     * @see OAuthPermission 
     * @return permissions
     */
    List<OAuthPermission> getPermissionsInfo(List<String> requestPermissions);
}
