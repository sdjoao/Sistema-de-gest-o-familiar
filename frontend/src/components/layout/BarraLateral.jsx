
//icones do lucide-react
import {
    Home,
    Users, 
    TrendingDown,
    TrendingUp,
    BookOpen,
    BarChart2,
    LogOut,
    Key
} from 'lucide-react'

import { NavLink } from 'react-router-dom'

const itensMenu = [
    {
        caminho: '/dashboard',
        icone: Home,
        rotulo: 'Inicio'
    },
    {
        caminho: '/membros',
        icone: Users,
        rotulo: 'Membros'
    },
    {
        caminho: 'despesas',
        icone: TrendingDown,
        rotulo: 'Despesas'
    },
    {
        caminho: 'receitas',
        icone: TrendingUp,
        rotulo: 'Receitas'
    },
    {
        caminho: '/diario',
        icone: BookOpen,
        rotulo: 'Diario'
    },
    {
        caminho: '/relatorios',
        icone: BarChart2,
        rotulo: 'Relatórios'
    }
]

function BarraLateral(){
    return (
        <aside className="h-screen w-64 bg-blue-700 flex flex-col">

            <div className="p-6 border-b border-blue-600">
                <h1 className="text-white text-xl font-bold">Sistema Gerencial</h1>
            </div>

            <nav className="flex-1 p-4 flex flex-col gap-1">
                {itensMenu.map((itemDoMenu) => {
                    const Icone = itemDoMenu.icone
                    return(
                        <NavLink
                            Key={itemDoMenu.caminho}
                            to={itemDoMenu.caminho}
                            className={({ isActive }) => 
                            `flex items-center gap-3 px-4 py-3 rounded-lg transition-colors
                            ${isActive ? 'bg-white text-blue-700 font-semibold' : 'text-blue-100 hover:bg-blue-600'
                            }`
                            }
                        >
                            <Icone size={20} />
                            <span>{itemDoMenu.rotulo}</span>
                        </NavLink>
                        )
                })}
            </nav>

            <div className="p-4 border-t border-blue-600">
                <button
                className="flex items-center gap-3 px-4 py-3 rounded-lg
                           text-blue-100 hover:bg-blue-600 transition-colors w-full"
                onClick={() => console.log('logout')} //implementar depois
                >
                <LogOut size={20} />
                <span>Sair</span>
                </button>
            </div>
        </aside>
    )
}


export default BarraLateral
