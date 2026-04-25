import { Outlet } from "react-router-dom";

import BarraLateral from "./BarraLateral";

function Layout(){
    return(
        <div className="flex h-screen bg-gray-50">
            <BarraLateral />
            
            <div className="flex-1 flex flex-col overflow-auto">
            
            <main className="flex-1 p-6">
                <Outlet/>
            </main>
            </div>
        </div>
    )
}

export default Layout