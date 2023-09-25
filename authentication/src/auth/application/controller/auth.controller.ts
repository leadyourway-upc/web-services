import {
  BadRequestException,
  Controller,
  Post,
  Request,
  UseGuards,
} from '@nestjs/common';
import { ApiTags, ApiOperation, ApiResponse, ApiBody } from '@nestjs/swagger'; // Importa los decoradores de Swagger
import { AuthService } from '../../domain/service/auth.service';
import { LocalAuthGuard } from './local-auth.guard';
import { CreateUserDto } from '../dto/create-user.dto';

@ApiTags('Auth')
@Controller('auth')
export class AuthController {
  constructor(private authService: AuthService) {}

  //@UseGuards(LocalAuthGuard)
  @Post('login')
  @ApiBody({
    description: 'Datos de inicio de sesión',
    type: CreateUserDto,
  })
  @ApiOperation({ summary: 'Iniciar sesión' })
  @ApiResponse({ status: 200, description: 'Inicio de sesión exitoso' })
  @ApiResponse({ status: 401, description: 'No autorizado' })
  async login(@Request() req) {
    return this.authService.login(req.body);
  }

  @Post('register')
  @ApiBody({
    description: 'Datos de registro',
    type: CreateUserDto,
  })
  @ApiOperation({ summary: 'Registrar usuario' })
  @ApiResponse({ status: 200, description: 'Registro exitoso' })
  @ApiResponse({ status: 401, description: 'No autorizado' })
  async register(@Request() req) {
    try {
      const user = await this.authService.register(req.body);
      const token = await this.authService.login(user);
      return { user, token };
    } catch (error) {
      throw new BadRequestException('Error al registrar al usuario');
    }
  }
}
