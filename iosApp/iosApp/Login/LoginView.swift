import SwiftUI
import shared

struct LoginView: View {
    @ObservedObject private var state: ObservableLoginState

    private let viewModel: LoginViewModel
//    private let onNext: () -> Void
    
    init(
        viewModel: LoginViewModel
//        onNext: @escaping () -> Void
    ) {
        self.viewModel = viewModel
//        self.onNext = onNext
        self.state = viewModel.observableState()
        observeState()
    }
    
    private func observeState() {
        viewModel.stateFlow.collect(
            collector: Collector<LoginState> { state in onStateReceived(state: state) }
        ) { error in
            print("Error ocurred during state collection")
        }
    }
    
    private func onStateReceived(state: LoginState) {
        self.state.value = state
        //        self.state.value.isLoggedIn.consume { _ in onNext() }
    }
    
    
    var body: some View {
        
        let emailBinding = Binding<String>(
            get: { state.value.email },
            set: { newValue in
                viewModel.onEmailChange(value: newValue)
            }
        )
        
        let passwordBinding = Binding<String>(
            get: { state.value.password },
            set: { newValue in
                viewModel.onPasswordChange(value: newValue)
            }
        )
        
        VStack {
            TextField("E-Mail", text: emailBinding, onEditingChanged: { _ in
                viewModel.onEmailChange(value: state.value.email)
            })
            .textFieldStyle(RoundedBorderTextFieldStyle())
            .padding()
            
            SecureField("Password", text: passwordBinding, onCommit: {
                viewModel.onPasswordChange(value: state.value.password)
            })
            .textFieldStyle(RoundedBorderTextFieldStyle())
            .padding()
            
            //                Text(#error ?? "")
            //                    .foregroundColor(.red)
            //                    .padding()
            
            Button(action: {
                viewModel.login()
            }) {
                Text("Login")
                    .padding()
                    .frame(maxWidth: .infinity)
                    .background(Color.blue)
                    .foregroundColor(.white)
                    .cornerRadius(8)
            }
            .padding()
            
            Spacer()
        }
    }
}

extension LoginViewModel {
    func observableState() -> ObservableLoginState {
        return (stateFlow.value as! LoginState).wrapAsObservable()
    }
}
