import SwiftUI
import shared

struct Resource: Identifiable {
    var id: UUID = UUID()
    var name: String
    var year: Int
    var color: Color
}

struct ResourceItem: View {
    var resource: Resource
    
    var body: some View {
        HStack(spacing: 16) {
            RoundedRectangle(cornerRadius: 8)
                .frame(width: 32, height: 32)
                .foregroundColor(resource.color)
            
            Text("\(resource.name) \(resource.year)")
                .font(.body)
        }
        .padding(EdgeInsets(top: 12, leading: 16, bottom: 12, trailing: 16))
        .background(Color.white)
        .cornerRadius(8)
        .shadow(radius: 4)
    }
}

struct ResourcesView: View {
    
    @State private var isLoading = false
    @State private var error: String? = nil
    @State private var resources: [Resource] = []
    
    let viewModel = ResourcesViewModelHelper()
    
    var body: some View {
        NavigationView {
            List(resources, id: \.id) { resource in
                if isLoading {
                    ProgressView()
                        .padding()
                } else {
                    ResourceItem(resource: resource)
                }
            }
            .navigationTitle("Resources")
            .onAppear {
                print("Loaded resources")
//                viewModel.loadResources()
            }
            .overlay(ProgressView().opacity(isLoading ? 1 : 0))
        }
    }
}
