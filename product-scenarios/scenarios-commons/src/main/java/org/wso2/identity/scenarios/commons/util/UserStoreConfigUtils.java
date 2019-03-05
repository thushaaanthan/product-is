/*
 *  Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.identity.scenarios.commons.util;

import org.wso2.carbon.identity.user.store.configuration.stub.dto.UserStoreDTO;
import org.wso2.identity.scenarios.commons.clients.service.client.UserStoreConfigAdminServiceClient;

/**
 * The Util class which carries common functionality required by the user store configuration scenarios
 */
public class UserStoreConfigUtils {

    /**
     * waiting until user store deploy
     *
     * @param userStoreConfigAdminServiceClient request userStore config admin client
     * @param domain request domain id
     * @return true/false whether it waits until the given time for user store deployment
     * @throws Exception If error occurs while waiting
     */
    public boolean waitForUserStoreDeployment(UserStoreConfigAdminServiceClient userStoreConfigAdminServiceClient,
            String domain) throws Exception {

        long waitTime = System.currentTimeMillis() + 30000; //wait for 45 seconds
        while (System.currentTimeMillis() < waitTime) {
            UserStoreDTO[] userStoreDTOs = userStoreConfigAdminServiceClient.getActiveDomains();
            for (UserStoreDTO userStoreDTO : userStoreDTOs) {
                if (userStoreDTO != null) {
                    if (userStoreDTO.getDomainId().equalsIgnoreCase(domain)) {
                        return true;
                    }
                }
            }
            Thread.sleep(500);
        }
        return false;
    }

    /**
     * wait until user store un-deploy
     *
     * @param userStoreConfigAdminServiceClient request userStore config admin client
     * @param domain  request domain id
     * @return true/false whether it waits until the given time for user store un-deployment
     * @throws Exception If error occurs while waiting
     */
    public boolean waitForUserStoreUnDeployment(UserStoreConfigAdminServiceClient userStoreConfigAdminServiceClient,
            String domain) throws Exception {

        long waitTime = System.currentTimeMillis() + 20000; //wait for 15 seconds
        while (System.currentTimeMillis() < waitTime) {
            UserStoreDTO[] userStoreDTOs = userStoreConfigAdminServiceClient.getActiveDomains();
            userStoreConfigAdminServiceClient.getActiveDomains();
            for (UserStoreDTO userStoreDTO : userStoreDTOs) {
                if (userStoreDTO != null) {
                    if (userStoreDTO.getDomainId().equalsIgnoreCase(domain)) {
                        Thread.sleep(500);
                    }
                }
            }
        }
        return true;
    }
}

