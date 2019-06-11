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
            marginLeft: theme.spacing(1),
            marginUp: theme.spacing(1),
            marginDown: theme.spacing(1),
            marginRight: theme.spacing(1),
        }

    });


class Index extends React.Component {
    GetCheck() {
        if (event.target.checked)
            console.log(event.target.value);
    }

    render() {
        const {classes} = this.props;
        const numeroDePreguntas = listaDeEnunciados.length;

        return (
                <MuiThemeProvider theme={theme}>
                    <Paper className={classes.root}>
                        <form  method="POST" action="ProbarExamen">
                            <input type="hidden" name="numeroDePreguntas" value={numeroDePreguntas} />
                            {listaDeEnunciados.map((enunciado, index) => (
                                                <div align="left"> <TextField
                                                        margin="dense"
                                                        variant="outlined" 
                                                        
        fullWidth                                                
        label={enunciado} 
                                                        name={"entrada_" + index} 
                                                        type="text"/></div>))}
                
                            {listaDeSoluciones.map((solucion, index) => (
                                        <input type="hidden" name={"solucion_" + index} value={solucion}/>
                                            ))}
                            {listaDePuntuaciones.map((puntuacion, index) => (
                                        <input type="hidden" name={"puntuacion_" + index} value={puntuacion}/>
                                            ))}
                            <Button type="submit" value="Calificar!">Calificar</Button>
                
                        </form>
                    </Paper>
                </MuiThemeProvider>
                );
    }
}

const App = withStyles(styles)(Index);
ReactDOM.render(<App />,
        document.getElementById('ProbarExamen'));
