const {
  Button,
  AppBar,
  Toolbar,
  Paper,
  Card,
  CardActionArea,
  CardActions,
  CardContent,
  CardMedia,
  Checkbox,
  IconButton,
  MenuIcon,
  TextField,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Grid,
  colors,
  createMuiTheme,
  CssBaseline,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  Icon,
  MuiThemeProvider,
  Typography,
  withStyles,
} = MaterialUI;

const theme = createMuiTheme({
  palette: {
    primary: {
      light: colors.purple[300],
      main: colors.purple[500],
      dark: colors.purple[700],
    },
    secondary: {
      light: colors.green[300],
      main: colors.green[500],
      dark: colors.green[700],
    },
  },
  typography: {
    useNextVariants: true,
  },
});

const styles = theme => ({
    root: {
    width: '100%',
    marginTop: theme.spacing(3),
    overflowX: 'auto',
  },
  table: {
    minWidth: 650,
  },
  button: {
    margin: theme.spacing(1),
  },
  textField: {
    marginLeft: theme.spacing.unit,
    marginRight: theme.spacing.unit,
    width: 200,
  },
});


function createData(name) {
  return { name };
}
var i;
var Preguntas=[];
var rows = [];
var Name;
for(i in preguntasDisponibles){
   rows.push(createData(preguntasDisponibles[i]));
}

class Index extends React.Component {
    
   GetCheck(){
       if(event.target.checked)
           if(Preguntas.indexOf(event.target.value) == -1)
            Preguntas.push(event.target.value);
	}0
        
   GetName = event => {
     Name=event.target.value;
  };
   
   CrearEx(){
       window.location.href="ModificarExamen?Quests="+Preguntas.toString()+"&TestName="+Name;
   }
   
        
  render() {
    const { classes } = this.props;
    return (
      <MuiThemeProvider theme={theme}>  
        <Paper className={classes.root}>
        <form method="post" action="ModificarExamen">
        <select id="Nombre"
                                        name="Nombre">{
                        examenesDisponibles.map(function (pregunta) {
                        return <option value={pregunta}>{pregunta}</option>;
                                    })
                                    }
                                </select>
      <Table className={classes.table}>
        <TableHead>
          <TableRow>
            <TableCell padding="checkbox"></TableCell>
            <TableCell>Pregunta</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map(row => (
            <TableRow key={row.name} role="checkbox">
                 <tableCell>
                 <Checkbox name="checkbox" value={row.name} onChange={this.GetCheck}></Checkbox>
                 </tableCell>
              <TableCell component="th" scope="row">
                {row.name}
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
                                      <input type="submit" name="submitButton" value="Modificar" />
                                <input type="submit" name="submitButton" value="Borrar" />

      </form>
    </Paper>
      </MuiThemeProvider>
    );
  }
}

const App = withStyles(styles)(Index);

ReactDOM.render(<App />, 
document.getElementById('ModificarExamen'));
