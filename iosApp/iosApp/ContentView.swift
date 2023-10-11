import SwiftUI
import shared

private let ResourcesRoute = "login"

struct ContentView: View {
    
    @State private var route = [String]()
    
    var body: some View {
        NavigationStack(path: $route) {
            LoginView(viewModel: LoginViewModelHelper().loginViewModel)
            .navigationDestination(for: String.self) { destination in
                switch (destination) {
                case ResourcesRoute:
                    ResourcesView()
                default:
                    Text("None")
                }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}