import { BrowserRouter, Navigate, Routes, Route } from "react-router-dom"
import Layout from "./components/layout/Layout"
import Dashboard from "./pages/Dashboard"


function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path ="/" element={ <Navigate to="/dashboard" replace />} />
        <Route path="/" element={<Layout />}>
          <Route path="dashboard" element={ <Dashboard /> } />
          <Route path="membros" element={ <div className="p-4">Membros</div>}/>
          <Route path="receitas" element={ <div className="p-4">Receitas</div>}/>
          <Route path="despesas" element={ <div className="p-4">Despesas</div>}/>
          <Route path="diario" element={ <div className="p-4">Diário</div>}/>
          <Route path="relatorios" element={ <div className="p-4">Relatórios</div>}/>
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
