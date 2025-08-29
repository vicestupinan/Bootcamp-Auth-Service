package co.com.vmestupinan.model.user.gateways;

import co.com.vmestupinan.model.user.User;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<User> save(User user);

    Mono<Boolean> existsByEmail(String email);
}
