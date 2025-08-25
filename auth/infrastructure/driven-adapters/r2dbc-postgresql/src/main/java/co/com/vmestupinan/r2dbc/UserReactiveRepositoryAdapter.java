package co.com.vmestupinan.r2dbc;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import co.com.vmestupinan.model.user.User;
import co.com.vmestupinan.model.user.gateways.UserRepository;
import co.com.vmestupinan.r2dbc.entity.UserEntity;
import co.com.vmestupinan.r2dbc.helper.UserReactiveAdapterOperations;
import reactor.core.publisher.Mono;

@Repository
public class UserReactiveRepositoryAdapter extends UserReactiveAdapterOperations<
    User, UserEntity, Long, UserReactiveRepository> implements UserRepository {

    public UserReactiveRepositoryAdapter(UserReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, User.class));
    }

    @Override
    public Mono<User> findByEmail(String email) {
        return repository.findByEmail(email)
                         .map(d -> mapper.map(d, User.class));
    }
}
