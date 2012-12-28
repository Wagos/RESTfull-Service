package services;

import constants.Constants;
import dto.UserList;
import dto.UserXmlDTO;
import entity.User;
import junit.framework.Assert;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Witkowsky Dmitry
 * Date: 22.12.12
 * Time: 17:12
 */

public class UserServiceTest extends Assert{

    private static final Integer BASE_ID=9990;
    private static final String BASE_URL="http://localhost:8080/RESTService/";

    private static final int MIN_USERS_COUNT=2;

    private List<User> testUsers;

    @Before
    public void createTestUsers(){
        testUsers=new ArrayList<>();
        for(int i=0;i<4;i++){
            User user=new User();
            String default_value=BASE_ID.toString()+i;
            user.setUsername(default_value);
            user.setEmail(default_value+"@asd.com");
            user.setPassword(default_value);
            testUsers.add(user);
        }
    }

    @Before
    public void logIn(){

    }

    @Test
    public void testSaveUser() throws Exception {
        String params;
        for(User user: testUsers) {
            params="?";
            params+="username="+user.getUsername();
            params+="&password="+user.getPassword();
            params+="&email="+user.getEmail();
            ClientRequest request=new ClientRequest(BASE_URL+Constants.USERS+params);
            Response.Status status=request.post(String.class).getResponseStatus();
            if(status!= Response.Status.CREATED){
                fail("Incorrect status");
            }
        }
    }

    @Test
    public void testGetAllUsers() throws Exception {

        ClientRequest request=new ClientRequest(BASE_URL+ Constants.USERS);
        request.accept(MediaType.APPLICATION_XML_TYPE);
        ClientResponse<String> stream=request.get(String.class);
        JAXBContext ctx = JAXBContext.newInstance(UserList.class);
        Unmarshaller unmarshaller = ctx.createUnmarshaller();
        UserList userXmlDTO= (UserList) unmarshaller.unmarshal(new ByteArrayInputStream(stream.getEntity().getBytes()));
        assertNotNull(userXmlDTO);
        if(userXmlDTO.getUsers().size()<MIN_USERS_COUNT){
            fail("Users must be more");
        }
    }

//    @Test
//    public void testGetUserById() throws Exception {
//        for(User user: testUsers){
//            ClientRequest request=new ClientRequest(BASE_URL+ Constants.USERS+"?username="+user.getUsername());
//            request.accept(MediaType.APPLICATION_XML_TYPE);
//            ClientResponse<String> stream=request.get(String.class);
//            JAXBContext ctx = JAXBContext.newInstance(UserXmlDTO.class);
//            Unmarshaller unmarshaller = ctx.createUnmarshaller();
//            UserXmlDTO userXmlDTO= (UserXmlDTO) unmarshaller.unmarshal(new ByteArrayInputStream(stream.getEntity().getBytes()));
//            Assert.assertNotNull(userXmlDTO);
//            user.setId(userXmlDTO.getId());
//        }
//    }

//    @Test
//    public void testGetUserPrivileges() throws Exception {
//
//    }
//
//    @Test
//    public void testGetUsersCount() throws Exception {
//
//    }
//
//    @Test
//    public void testAddFriend() throws Exception {
//
//    }
//
//    @Test
//    public void testDeleteUserFriend() throws Exception {
//
//    }
//
//    @Test
//    public void testDeleteUser() throws Exception {
//        for(User user: testUsers){
//            ClientRequest request=new ClientRequest(BASE_URL+ Constants.USERS+Constants.PATH_SEPARATOR+user.getId());
//            request.delete();
//        }
//    }

}
