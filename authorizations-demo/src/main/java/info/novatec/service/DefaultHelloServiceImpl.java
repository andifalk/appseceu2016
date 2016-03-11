package info.novatec.service;

import org.springframework.stereotype.Service;

/**
 * Created by afa on 04.03.16.
 */
@Service
public class DefaultHelloServiceImpl implements HelloService {

    @Override
    public String user() {
        return "User access";
    }

    @Override
    public String admin() {
        return "Admin access";
    }

    @Override
    public String hidden() {
        return "Hidden Admin access";
    }
}
