package com.example.app.domain.sign;

public class LogoutUseCase {
    private final SignUserRepository repo;

    public LogoutUseCase(SignUserRepository repo) {
        this.repo = repo;
    }

    public void execute(){
        repo.logout();
    }
}
