import shared

class ObservableLoginState : ObservableObject {
    @Published var value: LoginState
    
    init(value: LoginState) {
        self.value = value
    }
}

extension LoginState {
    func wrapAsObservable() -> ObservableLoginState {
        return ObservableLoginState(value: self)
    }
}
