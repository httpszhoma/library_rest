package zhoma.serveces;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zhoma.DTO.UserRequest;
import zhoma.exceptions.UserNotFoundException;
import zhoma.model.User;
import zhoma.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    public void addUser(UserRequest request) {
        User newuser = User.builder()
                .borrowedBooks(new ArrayList<>())
                .name(request.getName())
                .build();
        repository.save(newuser);
    }
    public void saveUser(User user){
        repository.save(user);
    }

    public List<User> getAll() {
       return repository.findAll();
    }
 public Optional<User> getByID(Integer id){
       return repository.findById(id);
 }

    public void deleteUserByID(int id) {
        repository.findById(id).orElseThrow(() -> new UserNotFoundException("User with this id = "+ id + " doesn't exist !!!"));
        repository.deleteById(id);
    }
}
