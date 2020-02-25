'use strict';
const axios = require("axios");
import Table from 'react-bootstrap/Table'
const React = require('react');
const ReactDOM = require('react-dom');

class App extends React.Component {

	constructor(props) {
		super(props);
		var currentDate = new Date();
		var formattedDate = 
		new Intl.DateTimeFormat(
			'fr-ca',
			 {year: 'numeric', month: '2-digit',day: '2-digit'}
			 ).format(currentDate);
		this.state = {
			currencies: [], 
			currentDate: formattedDate,
		    loggedInManager: this.props.loggedInManager};
		this.loadCurrencies(this.state.currentDate);
	}

	loadCurrencies(currentDate){
		axios.create({
			baseURL:"http://localhost:8080",
			headers:{
				'Access-Control-Allow-Headers':'Origin, X-Requested-With, Content-Type, Accept'
			}
		})
		.get("/api/currencies/search/findByDateOfRequest?dateOfRequest=" + currentDate)
		.then(
            res => {
                const currencies=res.data;
                this.setState({currencies})
                
            }
        );
	}

	render() {
		return (
			<>
			<div className="container">
				<p className="row">
					<button className="btn btn-primary col" 
						type="button"
						data-toggle="collapse" 
						href="#converter" 
						role="button" 
						aria-expanded="false" 
						aria-controls="converter">
						Конвертер
					</button>
					<button className="btn btn-primary col" 
						type="button" 
						data-toggle="collapse" 
						data-target="#currenciesList" 
						aria-expanded="false" 
						aria-controls="currenciesList">
						История котировок
					</button>
				</p>
				<div className="collapse" id="converter">
					<div className="card card-body">
						<CurrenciesConverter 
							currencies={this.state.currencies} 
							currentDate={this.state.currentDate}/>
					</div>
				</div>

				<div className="collapse" id="currenciesList">
					<div className="card card-body">
						<CurrencyHistory 
							currencies={this.state.currencies} 
							currentDate={this.state.currentDate}/>
					</div>
				</div>
			</div>
			</>
			
		)
	}
}

class CurrenciesConverter extends React.Component{

	constructor(props) {
		super(props);
		this.state= {
			fromCurrencyId: "RUB",
			toCurrencyId: "RUB",
			incomingCount: 0,
			currencies: this.props.currencies,
			dateOfIssue: this.props.currentDate
		}		
	}

	getRequest(){
		return {
		   fromCurrencyId: this.state.fromCurrencyId,
		   toCurrencyId: this.state.toCurrencyId,
		   dateOfIssue: this.props.currentDate,
		   incomingCount: this.state.incomingCount
	   };
   }

	convert(){
		axios.create({
			baseURL:"http://localhost:8080",
			headers:{
				'Access-Control-Allow-Headers':'Origin, X-Requested-With, Content-Type, Accept'
			}
		})
		.post("/currencies/convert", this.getRequest())
		.then(res =>{
			const outcomingCount = res.data;
			this.setState({outcomingCount});
		});
	}

	onChangeInputValue(event){
        this.setState({incomingCount: event.target.value});
	}

	onChangeIncomingCurrency(event){
		this.setState({fromCurrencyId: event.target.value });
	}

	onChangeOutcomingCurrency(event){
		this.setState({toCurrencyId: event.target.value });
	}
	
	render() {
		return (
			<>
				<h1 className="row" key="labelConverter">Конвертер</h1>
				<div className="row">
				<CurrencySelect 
						currencies={this.props.currencies}
						onChange={(event)=>this.onChangeIncomingCurrency(event)}
						className="custom-select col"
						name="Из" />
				<CurrencySelect 
						currencies={this.props.currencies}
						onChange={(event)=>this.onChangeOutcomingCurrency(event)}
						className="custom-select col"
						name="В" />
				</div>
				<div className="row">
					<input className="col form-control" value={this.state.incomingCount} 
						onChange={(event)=>this.onChangeInputValue(event)} type="text"></input>
					<input className="col form-control" value={this.state.outcomingCount} 
						onChange={(event)=>this.onChangeInputValue(event)}
						type="text" disabled></input>
				</div>
				<button 
					className="btn btn-success" 
					key="submitConvert" 
					name = "submit" 
					onClick={()=> this.convert()}>
					Конвертировать
				</button>
			</>
		
		)
	}
}

class CurrencyHistory extends React.Component {

    constructor(props) {
		super(props);
		this.state= {
			fromCurrencyId: "RUB",
			toCurrencyId: "RUB",
			incomingCount: 1,
			currencies: this.props.currencies,
			dateOfIssue: this.props.currentDate,
			isActionFired: false
		}		
	}

	onChangeDate(event){
		this.setState({dateOfIssue: event.target.value });
		this.loadCurrencies(this.state.dateOfIssue);
	}

	onChangeIncomingCurrency(event){
		this.setState({fromCurrencyId: event.target.value });
	}

	onChangeOutcomingCurrency(event){
		this.setState({toCurrencyId: event.target.value });
	}

	getRequest(){
		return {
		   fromCurrencyId: this.state.fromCurrencyId,
		   toCurrencyId: this.state.toCurrencyId,
		   dateOfIssue: this.state.dateOfIssue,
		   incomingCount: 1,
		   
	   };
   }

	convert(){
		axios.create({
			baseURL:"http://localhost:8080",
			headers:{
				'Access-Control-Allow-Headers':'Origin, X-Requested-With, Content-Type, Accept'
			}
		})
		.post("/currencies/convert", this.getRequest())
		.then(res =>{
			const outcomingCount = res.data;
			this.setState({outcomingCount});
			this.setState({isActionFired: true})
		});
	}
	loadCurrencies(currentDate){
		axios.create({
			baseURL:"http://localhost:8080",
			headers:{
				'Access-Control-Allow-Headers':'Origin, X-Requested-With, Content-Type, Accept'
			}
		})
		.get("/api/currencies/search/findByDateOfRequest?dateOfRequest=" + currentDate)
		.then(
            res => {
                const currencies=res.data;
                this.setState({currencies})
                
            }
        );
	}
    render(){
        return(
			<>
				<div className="align-center-my container">
					<div className="form-group">
						<h1 className="col-md-11">История</h1>
						<div className="row">
							<input 
								type="date" 
								className="form-control col" 
								value={this.state.dateOfIssue} 
								min="1996-09-01" max={this.props.currentDate}
								onChange={(event)=>this.onChangeDate(event)}/>
							<CurrencySelect 
								currencies={this.state.currencies}
								onChange={(event)=>this.onChangeIncomingCurrency(event)}
								className="custom-select col"
								name="Из" />
							<CurrencySelect 
								currencies={this.state.currencies}
								onChange={(event)=>this.onChangeOutcomingCurrency(event)} 
								className="custom-select col"
								name="В" />
						</div>
						<input 
							type="submit" 
							className="row btn btn-success" 
							onClick={()=> this.convert()}/>
					</div>
					{this.state.isActionFired?
					<Table striped bordered hover>         
						<thead>
							<tr>
								<th>Исходная валюта</th>
								<th>Целевая валюта</th>
								<th>Исходная сумма</th>
								<th>Получаемая сумма</th>
								<th>Дата</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>{this.state.fromCurrencyId}</td>
								<td>{this.state.toCurrencyId}</td>
								<td>{this.state.incomingCount}</td>
								<td>{this.state.outcomingCount}</td>
								<td>{this.state.dateOfIssue}</td>
							</tr>
						</tbody>
					</Table>:<div/>}
				</div>
			</>
        );
    }
}

class CurrencySelect extends React.Component {

	render(){
		return(
		<select 
			onChange={(event)=>this.props.onChange(event)}
			className={this.props.className} 
			defaultValue="RUB"
			name={this.props.name}>
			<option value="RUB">Российский рубль</option>
			{this.props.currencies.map((currency, index) => 
				<option key={currency.currencyId} value={currency.currencyId}>
					{currency.name}
				</option>
				)}
		</select>);
	}

}
ReactDOM.render(
	<App loggedInManager={document.getElementById('managername').innerHTML } />,
	document.getElementById('react')
)

