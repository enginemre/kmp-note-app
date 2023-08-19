import SwiftUI
import shared

struct ContentView: View {

	var body: some View {
        ZStack {
            ComposeView()
        }.preferredColorScheme(.dark)
            .edgesIgnoringSafeArea(.bottom)
        
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
